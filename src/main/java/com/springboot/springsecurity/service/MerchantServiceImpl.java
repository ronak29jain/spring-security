package com.springboot.springsecurity.service;

import com.springboot.springsecurity.entity.Address;
import com.springboot.springsecurity.entity.Merchant;
import com.springboot.springsecurity.entity.Product;
import com.springboot.springsecurity.model.AddressModel;
import com.springboot.springsecurity.model.MerchantModel;
import com.springboot.springsecurity.model.ProductModel;
import com.springboot.springsecurity.repository.MerchantMongoTemplate;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    MerchantMongoTemplate merchantMongoTemplate;

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    AddressServiceImpl addressServiceImpl;


    @Override
    @Transactional
    public MerchantModel addMerchant(MerchantModel merchantModel) {
        // TODO Auto-generated method stub
//		logger.debug("addmerchant method started running");
//		ModelMapper modelMapper = new ModelMapper();
//		Merchant merchant = modelMapper.map(merchantModel, Merchant.class);
//		Merchant merchant2 = merchantMongoTemplate.saveMerchant(merchant);
//		MerchantModel merchantModelSaved = modelMapper.map(merchant2, MerchantModel.class); 
//		return merchantModelSaved;


        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantModel.getMerchantName());
        merchant.setCompanyName(merchantModel.getCompanyName());
        merchant.setEmail(merchantModel.getEmail());
        merchant.setPhoneNumber(merchantModel.getPhoneNumber());
        Merchant savedMerchant = merchantMongoTemplate.saveMerchant(merchant);

        String id = savedMerchant.getId();

        ModelMapper modelMapper = new ModelMapper();

        AddressModel addressModel = merchantModel.getAddress();
        Address address = modelMapper.map(addressModel, Address.class);
        address.setMerchant(savedMerchant);
        Address savedAddress = addressServiceImpl.saveAddress(address);

        savedMerchant.setAddress(savedAddress);

        List<ProductModel> productModelList = merchantModel.getProducts();
        List<Product> savedProductsList = new ArrayList<Product>();
        productModelList.stream().forEach((productModel) -> {
            Product product = modelMapper.map(productModel, Product.class);
            product.setMerchant(savedMerchant);
            Product savedProduct = productServiceImpl.saveProduct(product);
            savedProductsList.add(savedProduct);
        });

        savedMerchant.setProducts(savedProductsList);

        Merchant completelySavedMerchant = merchantMongoTemplate.saveMerchant(merchant);

        MerchantModel savedMerchantModel = modelMapper.map(completelySavedMerchant, MerchantModel.class);

        return savedMerchantModel;

//		Iterator<ProductModel> productsListIterator = productModelList.iterator();

//		while (productsListIterator.hasNext()) {
//			 
//             Print all elements of List
//            System.out.println(productsListIterator.next());
//            productsListIterator.next().setMerchantId(id);
//        }

//		return null;
    }

    @Override
    public List<MerchantModel> getAllMerchants() {
        // TODO Auto-generated method stub
        List<Merchant> merchantsList = merchantMongoTemplate.findAllMerchants();

        ModelMapper modelMapper = new ModelMapper();

        List<MerchantModel> merchantModelsList = new ArrayList<MerchantModel>();

        for (int i = 0; i < merchantsList.size(); i++) {
            Merchant merchant = merchantsList.get(i);
            MerchantModel merchantModel = new MerchantModel();

            merchantModel.setMerchantName(merchant.getMerchantName());
            merchantModel.setCompanyName(merchant.getCompanyName());
            merchantModel.setEmail(merchant.getEmail());
            merchantModel.setPhoneNumber(merchant.getPhoneNumber());

            if (merchant.getAddress() != null) {
                AddressModel addressModel = modelMapper.map(merchant.getAddress(), AddressModel.class);
                merchantModel.setAddress(addressModel);

            }

            List<ProductModel> productModelsList = new ArrayList<ProductModel>();
            if (merchant.getProducts() != null) {
                merchant.getProducts().stream().forEach((product) -> {
                    productModelsList.add(modelMapper.map(merchant.getProducts(), ProductModel.class));
                    merchantModel.setProducts(productModelsList);
                });
            }

            merchantModelsList.add(merchantModel);
        }

        return merchantModelsList;
    }

    @Override
    public MerchantModel getMerchantById(String id) {
        // TODO Auto-generated method stub
        Merchant merchant = merchantMongoTemplate.findMerchantById(id);
        ModelMapper modelMapper = new ModelMapper();
        MerchantModel merchantModel = modelMapper.map(merchant, MerchantModel.class);
        return merchantModel;
    }

    @Override
    public MerchantModel getMerchantByName(String name) throws InstanceNotFoundException {
        // TODO Auto-generated method stub
        Merchant merchant = merchantMongoTemplate.findMerchantByName(name);
        if (merchant.getId() == null) {
//			throw new NameNotFoundException();
            throw new InstanceNotFoundException("Merchant by the name: " + name + " is not found");
        }
        ModelMapper modelMapper = new ModelMapper();
        MerchantModel merchantModel = modelMapper.map(merchant, MerchantModel.class);
        return merchantModel;
//		return null;
    }

    @Override
    public MerchantModel getMerchantByCompanyName(String companyName) {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public MerchantModel updateMerchant(MerchantModel merchantModel) {
        // TODO Auto-generated method stub
        String merchantName = merchantModel.getMerchantName();
//		MerchantModel fetchedMerchantModel = getMerchantByName(merchantName);
        Merchant merchant = merchantMongoTemplate.findMerchantByName(merchantName);
        merchant.setMerchantName(merchantName);
        merchant.setPhoneNumber(merchantModel.getPhoneNumber());
        merchant.setCompanyName(merchantModel.getCompanyName());
        merchant.setEmail(merchantModel.getEmail());
        Merchant completelySavedMerchant = merchantMongoTemplate.saveMerchant(merchant);
        ModelMapper modelMapper = new ModelMapper();
        MerchantModel savedMerchantModel = modelMapper.map(completelySavedMerchant, MerchantModel.class);

        return savedMerchantModel;
    }

    @Override
    public String deleteMerchant(String id) {
        // TODO Auto-generated method stub
        String msgString = merchantMongoTemplate.deleteMerchantById(id);
        return msgString;
    }

}
