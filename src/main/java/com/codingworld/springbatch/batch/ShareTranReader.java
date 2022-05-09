package com.codingworld.springbatch.batch;

import com.codingworld.springbatch.bean.ShareTransaction;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*@Component
@Scope("step")*/
public class ShareTranReader implements ItemReader<ShareTransaction> {

  @Override
  public ShareTransaction read()
      throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    return null;
  }
}
