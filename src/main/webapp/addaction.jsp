<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="menus.MenuDao"%>
<%
String menu_id = request.getParameter("menu_id");
String menu_name = request.getParameter("menu_name");
String menu_seq_in = request.getParameter("menu_seq");

int menu_seq = Integer.parseInt(menu_seq_in);

// 저장한다
MenuDao menuDao = new MenuDao(); // import 단축키: Ctrl + Shift + m
menuDao.addMenu(menu_id, menu_name, menu_seq);

// 목록으로 돌아간다
String loc = "menulist.jsp";
response.sendRedirect(loc);
%>