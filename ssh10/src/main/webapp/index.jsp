<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String url = request.getContextPath() + "/user";
  response.sendRedirect(url);
%>