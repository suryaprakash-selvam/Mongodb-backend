package com.EFMS.entity;


import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class UserAccount {
        @Field()
        private String userName;
        @Field()
        private String password;
        @Field()
        private String mobile_Number;
        @Field()
        @Indexed(unique = true)
        private String email;
        @Field()
        private String user_Type;


        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getMobile_Number() {
                return mobile_Number;
        }

        public void setMobile_Number(String mobile_Number) {
                this.mobile_Number = mobile_Number;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getUser_Type() {
                return user_Type;
        }

        public void setUser_Type(String user_Type) {
                this.user_Type = user_Type;
        }
}
