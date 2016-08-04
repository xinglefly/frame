package com.bytetime.Factory_modle;

/****
 * 工厂设计模式
 *
 * 优点：工厂模式完全符合设计原则，其降低了对象之间的耦合度。依赖于抽象的架构，将其实例化的任务交由子类去完成。
 *
 * 缺点：每次我们胃工厂方法模式添加新的产品时就需要编写一个新的产品类，还要引入抽象层，导致类结构的复杂化。
 */

public class ConcreateFactory extends Factory {

    @Override
    public <T extends Product> T createProduct(Class<T> clz) {
        Product product = null;
        try {
            product =  (Product) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }



    public  static void main(String [] args){
        Factory factory = new ConcreateFactory();
        factory.createProduct(ProductA.class).method();
    }


}