package com.dxc.JiraExtractor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by nguyennghi on 2019-04-14 13:05
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n" +
                "\n" +
                "    <title>404 Page Not Found</title>\n" +
                "\n" +
                "    <!-- Google font -->\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700\" rel=\"stylesheet\">\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat:900\" rel=\"stylesheet\">\n" +
                "\n" +
                "    <!-- Custom stlylesheet -->\n" +
                "    <link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\" />\n" +
                "\n" +
                "    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n" +
                "    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n" +
                "    <!--[if lt IE 9]>\n" +
                "    <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n" +
                "    <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n" +
                "    <![endif]-->\n" +
                "    <style>\n" +
                "        * {\n" +
                "            -webkit-box-sizing: border-box;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        #notfound {\n" +
                "            position: relative;\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "\n" +
                "        #notfound .notfound {\n" +
                "            position: absolute;\n" +
                "            left: 50%;\n" +
                "            top: 50%;\n" +
                "            -webkit-transform: translate(-50%, -50%);\n" +
                "            -ms-transform: translate(-50%, -50%);\n" +
                "            transform: translate(-50%, -50%);\n" +
                "        }\n" +
                "\n" +
                "        .notfound {\n" +
                "            max-width: 520px;\n" +
                "            width: 100%;\n" +
                "            line-height: 1.4;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .notfound .notfound-404 {\n" +
                "            position: relative;\n" +
                "            height: 240px;\n" +
                "        }\n" +
                "\n" +
                "        .notfound .notfound-404 h1 {\n" +
                "            font-family: 'Montserrat', sans-serif;\n" +
                "            position: absolute;\n" +
                "            left: 50%;\n" +
                "            top: 50%;\n" +
                "            -webkit-transform: translate(-50%, -50%);\n" +
                "            -ms-transform: translate(-50%, -50%);\n" +
                "            transform: translate(-50%, -50%);\n" +
                "            font-size: 252px;\n" +
                "            font-weight: 900;\n" +
                "            margin: 0px;\n" +
                "            color: #262626;\n" +
                "            text-transform: uppercase;\n" +
                "            letter-spacing: -40px;\n" +
                "            margin-left: -20px;\n" +
                "        }\n" +
                "\n" +
                "        .notfound .notfound-404 h1>span {\n" +
                "            text-shadow: -8px 0px 0px #fff;\n" +
                "        }\n" +
                "\n" +
                "        .notfound .notfound-404 h3 {\n" +
                "            font-family: 'Cabin', sans-serif;\n" +
                "            position: relative;\n" +
                "            font-size: 16px;\n" +
                "            font-weight: 700;\n" +
                "            text-transform: uppercase;\n" +
                "            color: #262626;\n" +
                "            margin: 0px;\n" +
                "            letter-spacing: 3px;\n" +
                "            padding-left: 6px;\n" +
                "        }\n" +
                "\n" +
                "        .notfound h2 {\n" +
                "            font-family: 'Cabin', sans-serif;\n" +
                "            font-size: 20px;\n" +
                "            font-weight: 400;\n" +
                "            text-transform: uppercase;\n" +
                "            color: #000;\n" +
                "            margin-top: 0px;\n" +
                "            margin-bottom: 25px;\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width: 767px) {\n" +
                "            .notfound .notfound-404 {\n" +
                "                height: 200px;\n" +
                "            }\n" +
                "            .notfound .notfound-404 h1 {\n" +
                "                font-size: 200px;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        @media only screen and (max-width: 480px) {\n" +
                "            .notfound .notfound-404 {\n" +
                "                height: 162px;\n" +
                "            }\n" +
                "            .notfound .notfound-404 h1 {\n" +
                "                font-size: 162px;\n" +
                "                height: 150px;\n" +
                "                line-height: 162px;\n" +
                "            }\n" +
                "            .notfound h2 {\n" +
                "                font-size: 16px;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<div id=\"notfound\">\n" +
                "    <div class=\"notfound\">\n" +
                "        <div class=\"notfound-404\">\n" +
                "            <h3>Oops! Page not found</h3>\n" +
                "            <h1><span>4</span><span>0</span><span>4</span></h1>\n" +
                "        </div>\n" +
                "        <h2>we are sorry, but the page you requested was not found</h2>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
