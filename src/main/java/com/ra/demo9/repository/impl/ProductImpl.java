package com.ra.demo9.repository.impl;

import com.ra.demo9.model.dto.ProductRequest;
import com.ra.demo9.model.entity.Product;
import com.ra.demo9.repository.IProductDao;
import com.ra.demo9.service.FileService;
import com.ra.demo9.service.ICategoryService;
import com.ra.demo9.service.IProductService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductImpl implements IProductDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    @Autowired
    private FileService fileService;

    @Override
    public List<Product> getProduct(Integer currentPage, Integer size) {
        Session session = sessionFactory.openSession();
        List<Product> products = null;
        try {
            products = session.createQuery("from Product ", Product.class)
                    .setFirstResult(currentPage * size)
                    .setMaxResults(size)
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }

    @Override
    public Product getProductById(Long pro_Id) {
        Session session = sessionFactory.openSession();
        try {
            Product product = session.get(Product.class, pro_Id);
            return product;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean insertProduct(Product pro) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(pro);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product, ProductRequest productRequest) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Lấy sản phẩm hiện có từ cơ sở dữ liệu
            Product existingProduct = session.get(Product.class, product.getProductId());
            if (existingProduct == null) {
                return false;
            }

            // Cập nhật chi tiết sản phẩm
            existingProduct.setProductName(product.getProductName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setSku(product.getSku());
            existingProduct.setUnitPrice(product.getUnitPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setUpdatedAt(product.getUpdatedAt());
            existingProduct.setCategory(product.getCategory());

            // Cập nhật hình ảnh của sản phẩm nếu có
            if (productRequest.getProductImage() != null && !productRequest.getProductImage().isEmpty()) {
                try {
                    String imageUrl = fileService.uploadFileToServer(productRequest.getProductImage());
                    existingProduct.setImage(imageUrl);
                } catch (Exception e) {
                    // Quản lý lỗi khi tải ảnh lên
                    e.printStackTrace();
                    transaction.rollback();
                    return false;
                }
            } else if (product.getProductId() != null) {
                String existingImageUrl = productService.getImageByProductId(product.getProductId());
                existingProduct.setImage(existingImageUrl);
            }

            // Lưu sản phẩm đã cập nhật
            session.update(existingProduct);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public boolean deleteProduct(Long pro_Id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(getProductById(pro_Id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public String getImageByProductId(Long pro_id) {
        Session session = sessionFactory.openSession();
        try {
            return (String) session.createQuery("select p.image from Product p where p.id = :id")
                    .setParameter("id", pro_id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> getProductByName(String name, Integer currentPage, Integer size) {
        Session session = sessionFactory.openSession();
        try {
            if (name == null || name.length() == 0)
                name = "%";
            else
                name = "%" + name + "%";
            List list = session.createQuery("from Product where productName like : proName")
                    .setParameter("proName", name)
                    .setFirstResult(currentPage * size)
                    .setMaxResults(size)
                    .getResultList();
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override


    public List<Product> sortByName(Integer currentPage, Integer size) {
        Session session = sessionFactory.openSession();
        List<Product> products = session.createQuery("from Product order by productName", Product.class)
                .setFirstResult(currentPage * size)
                .setMaxResults(size)
                .getResultList();
        session.close();
        return products;
    }

    public Long countAllProduct() {
        Session session = sessionFactory.openSession();
        try {
            return (Long) session.createQuery("select count(p.id) from Product p").getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }



    @Override
    public List<Product> listProductOfCategory(Long category_id, String name) {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("select p from Product p where p.category.categoryId=:id or p.productName like :name", Product.class)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("id", category_id)
                    .setMaxResults(6)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
        session.close();
        }
}


    public Long countProductByName(String name) {
        Session session = sessionFactory.openSession();
        name = "%" + name + "%";
        try {
            return (Long) session.createQuery("select count(p.id) from Product p where p.productName like :name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}