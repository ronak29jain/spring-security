package com.springboot.springsecurity.service;

import com.springboot.springsecurity.model.MerchantModel;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface MerchantService {

    public MerchantModel addMerchant(MerchantModel merchantModel);

    public List<MerchantModel> getAllMerchants();

    public MerchantModel getMerchantById(String id);

    public MerchantModel getMerchantByName(String name) throws InstanceNotFoundException;

    public MerchantModel getMerchantByCompanyName(String companyName);

    public MerchantModel updateMerchant(MerchantModel merchantModel);

    public String deleteMerchant(String id);
}
