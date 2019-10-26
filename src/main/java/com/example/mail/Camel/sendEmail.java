//package com.example.mail.Camel;
//
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class sendEmail extends RouteBuilder {
//
//    @Override
//    public void configure() throws Exception {
//
//        from("timer:read-table//start?period=60000")
//                .choice()
//                    .when()
//
//                    .otherwise()
//
//
////        from("imaps://imap.gmail.com?username=donald.duck.sber@gmail.com&password=Aq_12345"
////                + "&delete=false&unseen=true&consumer.delay=3000")
////                .setBody(e -> "New Mail")
////        .to("log:newmail");
//    }
//}
