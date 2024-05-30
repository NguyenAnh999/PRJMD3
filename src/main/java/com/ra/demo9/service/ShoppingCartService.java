package com.ra.demo9.service;

import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.ShoppingCart;
import com.ra.demo9.model.entity.Users;
import com.ra.demo9.repository.ShoppingCartDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartService
{
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    ShoppingCartDao shoppingCartDao;
    public void addToCart(Product product, Users user){
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .orderQuantity(1)
                .userId(user)
                .productId(product)
                .build();
        shoppingCartDao.addShoppingCart(shoppingCart);
    }
    public Double getShoppingCartTotal(Long userId){
       return shoppingCartDao.getShoppingCartTotal(userId);
    }

    public List<ShoppingCart> getShoppingCart()
    {
      return   shoppingCartDao.getAllShoppingCarts();
    }


    public void deleteAllShopCart (){
        shoppingCartDao.deleteAllShoppingCart();
    }

    public void deleteItemShoppingCart(Integer shoppingCartId)
    {
        shoppingCartDao.deleteItemShoppingCart(shoppingCartId);
    }
}
