package com.bazan.hospital.shared.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Response<T> {
    public boolean success;
    public String message;
    public T data;
}
