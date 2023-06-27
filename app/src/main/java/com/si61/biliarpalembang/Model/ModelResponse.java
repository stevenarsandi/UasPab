package com.si61.biliarpalembang.Model;

import java.util.List;

public class ModelResponse {
    private String kode,pesan;
    private List<ModelBiliar> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelBiliar> getData() {
        return data;
    }
}
