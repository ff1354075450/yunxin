package com.yunxin.enums;

/**
 * create by ff on 2017/8/29
 */
public enum StudentEnum {
    GOOD(1,"好学生"),
    BAD(2,"坏学生"),
    VERGOOD(3,"很好的学生"),
    VERBAD(4,"很坏的学生");


    private int state;

    private String stateInfo;

    StudentEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static StudentEnum stateOf(int index){
        for (StudentEnum stae:values()){
            if(stae.getState() == index){
                return stae;
            }
        }
        return null;
    }
}
