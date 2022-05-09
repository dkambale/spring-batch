package com.codingworld.springbatch.batch;

import com.codingworld.springbatch.ShareTranRepo;
import com.codingworld.springbatch.bean.ShareTransaction;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShareTranWritter implements ItemWriter<ShareTransaction> {

  @Autowired
  ShareTranRepo shareTranRepo;

  @Override
  public void write(List<? extends ShareTransaction> list) throws Exception {

    shareTranRepo.saveAll(list);
    System.out.println("Size of Record stored:"+list.size());
  }
}
