package com.example.santa.user.controller;

import com.example.santa.user.service.AdminService;
import com.example.santa.user.vo.AdminVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 관리자 관리 페이지 렌더링
    @GetMapping("/management")
    public String adminManagementPage() {
        return "user/admin-management"; // templates/user/admin-management.html
    }

    // 관리자 목록 API
    @ResponseBody
    @GetMapping("/list")
    public List<AdminVO> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // 권한 수정 API
    @ResponseBody
    @PutMapping("/role")
    public ResponseEntity<String> updateRole(@RequestParam("employeeCode") String employeeCode,
                                             @RequestParam("role") String role) {
        boolean updated = adminService.updateRole(employeeCode, role);
        return updated ? ResponseEntity.ok("Role updated successfully")
                : ResponseEntity.badRequest().body("Failed to update role");
    }

    // 관리자 삭제 API
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") int adminId) {
        boolean deleted = adminService.deleteAdmin(adminId);
        return deleted ? ResponseEntity.ok("Admin deleted successfully")
                : ResponseEntity.badRequest().body("Failed to delete admin");
    }
}
