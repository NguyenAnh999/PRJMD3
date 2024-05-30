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
    public WishList getWishListById(Long wl_Id){
        return wishlistDao.getWishListById(wl_Id);
    }

    public boolean deleteWishList(Long userId,Long productId){
        WishList wishList = wishlistDao.getWishListByIdProduct(userId,productId);
       return wishlistDao.deleteWishList(wishList);
    }
}
