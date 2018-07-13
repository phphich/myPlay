package controllers;

import models.Product;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.showOrder;
import views.html.showProduct;

import javax.persistence.Cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application extends Controller {
    public static List<Product> productList=new ArrayList<Product>();
    public static List<Product> banket =new ArrayList<Product>();

    static {
        productList.add(new Product("P001", "Monitor", 1500.00));
        productList.add(new Product("P002", "Computer", 3500.00));
        productList.add(new Product("P003", "Keyboard", 500.00));
        productList.add(new Product("P004", "Speaker", 1000.00));
        productList.add(new Product("P005", "Mouse", 750.00));
    }

    public static Result order() {
        return ok(showProduct.render(productList));
    }

    public static Result getOrder() {
        DynamicForm myForm = Form.form().bindFromRequest();
        Map<String, String[]> map = request().body().asFormUrlEncoded();
        String[] select = map.get("select"); // get selected topics
        for(int i=0;i<select.length;i++){
            for(int j=0;j<productList.size();j++){
                if(select[i].equals(productList.get(j).getId())){
                    banket.add( productList.get(i));
                    break;
                }
            }
        }
        Cache chk;
        return ok(showOrder.render(banket));
    }

}
