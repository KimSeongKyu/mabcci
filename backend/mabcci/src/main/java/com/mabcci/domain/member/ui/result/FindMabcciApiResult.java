package com.mabcci.domain.member.ui.result;

public class FindMabcciApiResult<T> {

    private T mabccies;

    private FindMabcciApiResult() {
    }

    public FindMabcciApiResult(T mabccies) {
        this.mabccies = mabccies;
    }

    public T getMabccies() {
        return mabccies;
    }

}
