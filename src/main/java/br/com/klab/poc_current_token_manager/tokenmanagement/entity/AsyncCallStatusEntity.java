package br.com.klab.poc_current_token_manager.tokenmanagement.entity;

import br.com.klab.poc_current_token_manager.tokenmanagement.values.AsyncCallStatus;
import com.google.gson.Gson;

import java.util.Date;

public class AsyncCallStatusEntity {

    private AsyncCallStatus asyncCallStatus;

    private Date date;

    private AsyncCallStatusEntity(AsyncCallStatus asyncCallStatus) {
        this.setAsyncCallStatus(asyncCallStatus);
        this.setDate(new Date());
    }

    public static AsyncCallStatusEntity start(AsyncCallStatus asyncCallStatus) {
        return new AsyncCallStatusEntity(asyncCallStatus);
    }

    public AsyncCallStatus getAsyncCallStatus() {
        return this.asyncCallStatus;
    }

    private void setAsyncCallStatus(AsyncCallStatus asyncCallStatus) {
        this.asyncCallStatus = asyncCallStatus;
    }

    public Date getDate() {
        return this.date;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    public Boolean isRunning() {
        return this.getAsyncCallStatus().equals(AsyncCallStatus.RUNNING);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
