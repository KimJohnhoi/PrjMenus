<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="menus.MenuDao"%>
<%
String menu_id = request.getParameter("menu_id");

// 삭제한다.
MenuDao menuDao = new MenuDao(); // import 단축키: Ctrl + Shift + m
menuDao.delMenu(menu_id);

// 목록으로 돌아간다
String loc = "menulist.jsp";
response.sendRedirect(loc);
%>