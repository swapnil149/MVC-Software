package jrails;

import java.util.ArrayList;
import java.util.List;

public class View {
    public static Html empty() {
        return new Html("");
    }

    public static Html br() {
        return new Html("<br/>");
    }

    public static Html t(Object o) {
        return new Html(o.toString());
    }

    public static Html p(Html child) {
        return new Html("<p>" + child.getContent() + "</p>");
    }

    public static Html div(Html child) {
        return new Html("<div>" + child.getContent() + "</div>");
    }

    public static Html strong(Html child) {
        return new Html("<strong>" + child.getContent() + "</strong>");
    }

    public static Html h1(Html child) {
        return new Html("<h1>" + child.getContent() + "</h1>");
    }

    public static Html tr(Html child) {
        return new Html("<tr>" + child.getContent() + "</tr>");
    }

    public static Html th(Html child) {
        return new Html("<th>" + child.getContent() + "</th>");
    }

    public static Html td(Html child) {
        return new Html("<td>" + child.getContent() + "</td>");
    }

    public static Html table(Html child) {
        return new Html("<table>" + child.getContent() + "</table>");
    }

    public static Html thead(Html child) {
        return new Html("<thead>" + child.getContent() + "</thead>");
    }

    public static Html tbody(Html child) {
        return new Html("<tbody>" + child.getContent() + "</tbody>");
    }

    public static Html textarea(String name, Html child) {
        return new Html("<textarea name=\"" + name + "\">" + child.getContent() + "</textarea>");
    }

    public static Html link_to(String text, String url) {
        return new Html("<a href=\"" + url + "\">" + text + "</a>");
    }

    public static Html form(String action, Html child) {
        return new Html("<form action=\"" + action + "\" accept-charset=\"UTF-8\" method=\"post\">" + child.getContent() + "</form>");
    }

    public static Html submit(String value) {
        return new Html("<input type=\"submit\" value=\"" + value + "\"/>");
    }
}

