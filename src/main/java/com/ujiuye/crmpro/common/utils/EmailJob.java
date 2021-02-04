package com.ujiuye.crmpro.common.utils;

import com.ujiuye.crmpro.message.pojo.Email;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.mail.MessagingException;

public class EmailJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Email email=(Email)context.getJobDetail().getJobDataMap().get("email");
        try {
            EmailUtil.sendEmail(email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
