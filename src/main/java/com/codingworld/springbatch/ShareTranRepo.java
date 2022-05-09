package com.codingworld.springbatch;

import com.codingworld.springbatch.bean.ShareTransaction;
import org.springframework.data.repository.CrudRepository;

public interface ShareTranRepo extends CrudRepository<ShareTransaction,Integer> {

}
