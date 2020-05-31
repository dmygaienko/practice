package com.mygaienko.patterns.creational.builder;


/**
 * Created by dmygaenko on 25/03/2016.
 */
public class Bean {

    private String stringA;
    private String stringB;
    private String stringC;
    private String stringD;
    private String stringE;
    private String stringF;
    private String stringG;
    private String stringH;

    private Bean() {

    }

    public static class BeanBuilder {
        private Bean bean;

        public BeanBuilder() {
           bean = new Bean();
        }

        public BeanBuilder setStringA(String string){
            bean.stringA = string;
            return this;
        }

        public BeanBuilder setStringB(String string){
            bean.stringB = string;
            return this;
        }

        public BeanBuilder setStringC(String string){
            bean.stringC = string;
            return this;
        }

        public BeanBuilder setStringD(String string){
            bean.stringD = string;
            return this;
        }

        public BeanBuilder setStringE(String string){
            bean.stringE = string;
            return this;
        }

        public BeanBuilder setStringF(String string){
            bean.stringF = string;
            return this;
        }

        public BeanBuilder setStringG(String string){
            bean.stringG = string;
            return this;
        }

        public BeanBuilder setStringH(String string){
            bean.stringH = string;
            return this;
        }

        public Bean build() {
            return bean;
        }
    }

    @Override
    public String toString() {
        return "Bean{" +
                "stringA='" + stringA + '\'' +
                ", stringB='" + stringB + '\'' +
                ", stringC='" + stringC + '\'' +
                ", stringD='" + stringD + '\'' +
                ", stringE='" + stringE + '\'' +
                ", stringF='" + stringF + '\'' +
                ", stringG='" + stringG + '\'' +
                ", stringH='" + stringH + '\'' +
                '}';
    }
}
