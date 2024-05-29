package com.ra.demo9.service;

import com.ra.demo9.model.entity.Product;
import com.ra.demo9.model.entity.WishList;
import com.ra.demo9.repository.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    WishlistDao wishlistDao;
    public List<Product> getAllWishList(Long userId){
        return wishlistDao.getAllWishList(userId);
    }

    public boolean addWishList(WishList wishList){
        return wishlistDao.addWishList(wishList);
    }

    public void deleteWishList(Integer wishListId){
        wishlistDao.deleteWishList(wishListId);
    }
}
