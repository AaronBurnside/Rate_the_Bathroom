package com.example.rate_the_restroom;


    public class A_Comment {

        private String content, Building, Floor, Room;
        private String timestamp;
        private String Compiled_Comment;


        public A_Comment() {
        }

        public A_Comment(String content, String Building, String Floor, String Room, String timestamp) {
            this.content = content;
            this.Building = Building;
            this.Floor = Floor;
            this.Room = Room;
            this.timestamp = timestamp;

        }


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getBuilding() {
            return Building;
        }

        public void setBuilding(String Building) {
            this.Building = Building;
        }

        public String getFloor() {
            return Floor;
        }

        public void setFloor(String Floor) {
            this.Floor = Floor;
        }

        public String getRoom() {
            return Room;
        }

        public void setRoom(String Room) {
            this.Room = Room;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
        public void CompileMessage(){
            this.Compiled_Comment = "{" + this.content + "},{" + this.Building + "},{" + this.Floor + "},{"  + this.Room + "},{" + this.timestamp + "}";
        }
        public String getComment(){return Compiled_Comment; }
    }
