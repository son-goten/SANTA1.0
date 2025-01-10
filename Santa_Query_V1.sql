	USE SANTA;
    
    -- 1. Categories 테이블
	CREATE TABLE Categories (
		category_id INT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(20) NOT NULL UNIQUE, -- 카테고리 이름
		description VARCHAR(100) -- 카테고리 설명
	);

	-- 2. Manufacturers 테이블
	CREATE TABLE Manufacturers (
		manufacturer_id INT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(50) NOT NULL,
		location VARCHAR(20),
		contact VARCHAR(50) CHECK (
			contact REGEXP '^010-[0-9]{3,4}-[0-9]{4}$' OR
			contact REGEXP '^(02|0[3-6][1-4]|05[1-5]|06[1-4])-[0-9]{3}-[0-9]{4}$'
		)
	);

	-- 3. Suppliers 테이블
	CREATE TABLE Suppliers (
		supplier_id INT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(100) NOT NULL,
		contact VARCHAR(50) CHECK (
			contact REGEXP '^010-[0-9]{3,4}-[0-9]{4}$' OR
			contact REGEXP '^(02|0[3-6][1-4]|05[1-5]|06[1-4])-[0-9]{3}-[0-9]{4}$'
		),
		location VARCHAR(255)
	);

	-- 4. Warehouses 테이블
	CREATE TABLE Warehouses (
		warehouse_id INT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(20) NOT NULL,
		location VARCHAR(25) NOT NULL, 
		capacity INT NOT NULL CHECK (capacity >= 0) -- 창고 용량은 음수가 될 수 없음
	);

	-- 5. Branches 테이블
	CREATE TABLE Branches (
		branch_id INT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(100) NOT NULL,
		location VARCHAR(255) NOT NULL
	);

    -- 5.5. 사원코드- 권한 연결테이블
    CREATE TABLE EmployeeRoles (
                                   employee_code VARCHAR(15) PRIMARY KEY,            -- 사원코드 (고유)
                                   role ENUM('root', 'general') NOT NULL             -- 역할 (루트 관리자, 일반 관리자)
    );

	-- 6. Administrators 테이블
    CREATE TABLE Administrators (
            admin_id INT AUTO_INCREMENT PRIMARY KEY,          -- 관리자 ID (고유)
            user_id VARCHAR(50) UNIQUE NOT NULL,              -- 로그인용 사용자 ID (중복 불가)
            password VARCHAR(255) NOT NULL,                   -- 로그인용 비밀번호 (암호화 필요)
            employee_code VARCHAR(50) NOT NULL,               -- 사원코드
            role ENUM('root', 'general') NOT NULL,            -- 관리자 역할 (루트 관리자, 일반 관리자)
            name VARCHAR(100) NOT NULL,                       -- 관리자 이름
            email VARCHAR(100) UNIQUE NOT NULL,               -- 이메일 (중복 불가)
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- 생성일
            CONSTRAINT fk_employee_code FOREIGN KEY (employee_code) REFERENCES EmployeeRoles(employee_code)
    );


    -- 7. Products 테이블
	CREATE TABLE Products (
		product_id INT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(255) NOT NULL,
		description TEXT,
		category_id INT,
		price DOUBLE NOT NULL,
		created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		manufacturer_id INT,
		FOREIGN KEY (category_id) REFERENCES Categories(category_id) ON DELETE SET NULL,
		FOREIGN KEY (manufacturer_id) REFERENCES Manufacturers(manufacturer_id) ON DELETE SET NULL
	);

	-- 8. Administrator_Warehouses 테이블
	CREATE TABLE Administrator_Warehouses (
		admin_warehouse_id INT AUTO_INCREMENT PRIMARY KEY, -- 관계 ID
		admin_id INT NOT NULL, -- 관리자 ID
		warehouse_id INT NOT NULL, -- 창고 ID
		assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 할당 시간
		FOREIGN KEY (admin_id) REFERENCES Administrators(admin_id) ON DELETE CASCADE, -- 관리자가 삭제되면 관련 데이터 삭제
		FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id) ON DELETE CASCADE -- 창고 삭제 시 관련 데이터 삭제
	);

	-- 9. Warehouse_Inventory 테이블
	CREATE TABLE Warehouse_Inventory (
		inventory_id INT AUTO_INCREMENT PRIMARY KEY,
		warehouse_id INT NOT NULL,
		product_id INT, -- NULL 가능으로 변경
		quantity INT NOT NULL CHECK (quantity >= 0), -- 재고 수량은 음수가 될 수 없음
		last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id) ON DELETE CASCADE,
		FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE SET NULL, -- 제품 삭제 시 NULL 처리
		UNIQUE (warehouse_id, product_id)
	);


	-- 10. Orders 테이블
	CREATE TABLE Orders (
		order_id INT AUTO_INCREMENT PRIMARY KEY,
		warehouse_id INT NOT NULL, -- 배송될 창고
		branch_id INT, -- 주문 발생 지점 (NULL 허용)
		order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
		status ENUM('대기', '완료', '취소') NOT NULL,
		FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id) ON DELETE CASCADE,
		FOREIGN KEY (branch_id) REFERENCES Branches(branch_id) ON DELETE SET NULL -- 지점 삭제 시 주문 기록 보존
	);

	-- 11. Supplier_Products 테이블
	CREATE TABLE Supplier_Products (
		supplier_product_id INT AUTO_INCREMENT PRIMARY KEY, -- PK
		supplier_id INT NOT NULL, -- 공급자 ID FK
		product_id INT NOT NULL, -- 제품 ID FK
		UNIQUE(supplier_id, product_id), -- 중복 방지: 한 공급자-제품 조합은 한 번만 저장
		FOREIGN KEY (supplier_id) REFERENCES Suppliers(supplier_id) ON DELETE CASCADE, -- 공급자 삭제 시 관련 데이터 삭제
		FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE -- 제품 삭제 시 관련 데이터 삭제
	);

	-- 12. Incoming 테이블
	CREATE TABLE Incoming (
		incoming_id INT AUTO_INCREMENT PRIMARY KEY,
		warehouse_id INT NOT NULL,
		product_id INT NOT NULL,
		supplier_id INT,
		quantity INT NOT NULL CHECK (quantity > 0), -- 입고 수량은 0보다 커야 함
		incoming_date DATETIME DEFAULT CURRENT_TIMESTAMP,
		FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id) ON DELETE CASCADE,
		FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE CASCADE,
		FOREIGN KEY (supplier_id) REFERENCES Suppliers(supplier_id) ON DELETE SET NULL
	);

	-- 13. Outgoing 테이블
    CREATE TABLE Outgoing (
                              outgoing_id INT AUTO_INCREMENT PRIMARY KEY,
                              warehouse_id INT NOT NULL,
                              product_id INT, -- NULL 가능으로 변경
                              order_id INT, -- 주문 ID와 연결
                              branch_id INT, -- 지점 ID와 연결
                              quantity INT NOT NULL CHECK (quantity > 0), -- 출고 수량은 0보다 커야 함
                              outgoing_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                              status ENUM('대기', '완료', '취소') NOT NULL,
                              FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id) ON DELETE CASCADE,
                              FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE SET NULL, -- 제품 삭제 시 NULL 처리
                              FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE SET NULL,
                              FOREIGN KEY (branch_id) REFERENCES Orders(branch_id) ON DELETE SET NULL
    );

-- 14. Boards 테이블
CREATE TABLE Boards (
    board_id INT AUTO_INCREMENT PRIMARY KEY, -- 게시글 또는 댓글 ID
    board_type ENUM('NOTICE', 'GENERAL') NOT NULL, -- 게시판 유형 (공지사항 또는 일반 게시판)
    author_id INT NOT NULL, -- 작성자 ID (참조: Administrators 또는 다른 사용자 테이블과 연계 가능)
    title VARCHAR(255), -- 게시글 제목 (댓글인 경우 NULL)
    content TEXT NOT NULL, -- 게시글 또는 댓글 내용
    parent_id INT DEFAULT NULL, -- 댓글인 경우 참조할 게시글 ID (NULL이면 게시글)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 작성일
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    FOREIGN KEY (author_id) REFERENCES Administrators(admin_id) ON DELETE CASCADE, -- 작성자 삭제 시 연쇄 삭제
    FOREIGN KEY (parent_id) REFERENCES Boards(board_id) ON DELETE CASCADE -- 부모 게시글 삭제 시 댓글도 삭제
);

-- 15. Transit 테이블
    CREATE TABLE Transit (
                             transit_id INT AUTO_INCREMENT PRIMARY KEY,
                             outgoing_id INT NOT NULL,
                             warehouse_id INT,
                             branch_id INT,
                             product_id INT,
                             quantity INT NOT NULL CHECK (quantity > 0),
                             transit_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                             status ENUM('배송대기', '배송완료', '배송취소', '배송중') NOT NULL,
                             FOREIGN KEY (outgoing_id) REFERENCES Outgoing(outgoing_id) ON DELETE CASCADE,
                             FOREIGN KEY (warehouse_id) REFERENCES Warehouses(warehouse_id) ON DELETE SET NULL, -- 제품 삭제 시 NULL 처리
                             FOREIGN KEY (branch_id) REFERENCES Branches(branch_id) ON DELETE SET NULL,
                             FOREIGN KEY (product_id) REFERENCES Products(product_id) ON DELETE SET NULL
    );

-- 16. 패스워드 리셋 토큰 테이블
    CREATE TABLE password_reset_tokens (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           email VARCHAR(255) NOT NULL,
                                           token VARCHAR(255) NOT NULL,
                                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- 1. Categories 테이블
INSERT INTO Categories (name, description) VALUES
('영양제', '비타민, 미네랄, 보충제 등'),
('건강식품', '면역력 강화, 체중 조절 제품'),
('운동보조제', '근육 성장 및 회복 보조제'),
('스킨케어', '피부 건강 관련 제품'),
('의료기기', '가정용 의료기기');

-- 2. Manufacturers 테이블
INSERT INTO Manufacturers (name, location, contact) VALUES
('한국영양제', '서울', '010-1234-5678'),
('굿헬스코리아', '경기', '031-234-5678'),
('바디케어', '부산', '051-345-6789'),
('올가헬스', '대구', '053-456-7890'),
('이너뷰티', '광주', '062-567-8901');

-- 3. Suppliers 테이블
INSERT INTO Suppliers (name, contact, location) VALUES
('굿헬스 공급사', '010-2222-3333', '서울'),
('바디케어 유통', '02-123-4567', '부산'),
('올가헬스 물류', '032-444-5555', '인천'),
('이너뷰티 공급사', '010-3333-4444', '광주'),
('맘앤베이비 유통', '010-5555-6666', '대전');

-- 4. Warehouses 테이블
INSERT INTO Warehouses (name, location, capacity) VALUES
('서울 헬스케어 창고', '서울', 1000),
('부산 헬스케어 창고', '부산', 2000),
('인천 헬스케어 창고', '인천', 1500),
('대구 헬스케어 창고', '대구', 1800),
('광주 헬스케어 창고', '광주', 1200);

-- 5. Branches 테이블
INSERT INTO Branches (name, location) VALUES
('서울헬스센터', '서울특별시 강남구 삼성동'),
('부산헬스센터', '부산광역시 사상구 괘법동'),
('인천헬스센터', '인천광역시 남동구 구월동'),
('대구헬스센터', '대구광역시 동구 동대구역'),
('광주헬스센터', '광주광역시 남구 봉선동');

-- 5.5
INSERT INTO EmployeeRoles (employee_code, role) VALUES
('EMP001', 'root'),    -- 루트 관리자 권한
('EMP002', 'general'), -- 일반 관리자 권한
('EMP003', 'general'); -- 일반 관리자 권한

-- 6. Administrators 테이블
INSERT INTO Administrators (user_id, password, role) VALUES
('admin1', 'password1', 'root'),
('admin2', 'password2', 'general'),
('admin3', 'password3', 'general'),
('admin4', 'password4', 'general'),
('admin5', 'password5', 'root');

-- 7. Products 테이블
INSERT INTO Products (name, description, category_id, price, manufacturer_id) VALUES
('비타민C', '고용량 비타민C 1000mg', 1, 25000, 1),
('오메가3', '고농축 오메가3', 1, 30000, 2),
('프로틴 파우더', '운동 보조용 단백질 파우더', 3, 50000, 3),
('멀티비타민', '종합 비타민제', 1, 40000, 4),
('콜라겐', '피부 건강을 위한 콜라겐', 4, 45000, 5);

-- 8. Administrator_Warehouses 테이블
INSERT INTO Administrator_Warehouses (admin_id, warehouse_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);

-- 9. Warehouse_Inventory 테이블
INSERT INTO Warehouse_Inventory (warehouse_id, product_id, quantity) VALUES
(1, 1, 500),
(1, 2, 300),
(2, 3, 400),
(3, 4, 200),
(4, 5, 150);

-- 10. Orders 테이블
INSERT INTO Orders (warehouse_id, branch_id, status) VALUES
(1, 1, '대기'),
(2, 2, '완료'),
(3, 3, '취소');

-- 11. Supplier_Products 테이블
INSERT INTO Supplier_Products (supplier_id, product_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);

-- 12. Incoming 테이블
INSERT INTO Incoming (warehouse_id, product_id, supplier_id, quantity) VALUES
(1, 1, 1, 200),
(2, 3, 3, 300),
(4, 5, 5, 100);

-- 13. Outgoing 테이블
INSERT INTO Outgoing (warehouse_id, product_id, order_id, branch_id, quantity, status) VALUES
(3, 2, 1, 1, 80, '대기'),
(4, 3, 3, 2, 90, '완료'),
(1, 4, 2, 3, 40, '취소');

-- 게시판 샘플 데이터 삽입, 댓글 데이터 삽입
-- 일단 생략

-- 15. Transit 테이블
    INSERT INTO Transit (outgoing_id, warehouse_id, branch_id, product_id, quantity, status) VALUES
    (1, 3, 1, 2, 80, '배송중'),
    (2, 4, 2, 3, 90, '배송대기'),
    (3, 1, 3, 4, 40, '배송취소')
    ;