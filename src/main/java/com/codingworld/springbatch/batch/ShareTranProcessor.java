package com.codingworld.springbatch.batch;

import com.codingworld.springbatch.ShareTranRepo;
import com.codingworld.springbatch.bean.ShareTransaction;
import java.util.Optional;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShareTranProcessor implements ItemProcessor<ShareTransaction, ShareTransaction> {

  @Autowired
  ShareTranRepo shareTranRepo;

  @Override
  public ShareTransaction process(ShareTransaction shareTransaction) throws Exception {
    Optional<ShareTransaction> opt=shareTranRepo.findById(shareTransaction.getShareCode());
    if(opt.isPresent()) {
      Integer amount=Integer.parseInt(shareTransaction.getAmount());
      ShareTransaction shareTransaction1=opt.get();
      Integer amountO=Integer.parseInt(shareTransaction1.getAmount());
      Integer total=amountO+amount;
      shareTransaction.setAmount(""+total);
    }
    return shareTransaction;
  }
}
