package jp.falsystack.catalogservice.service;

import jp.falsystack.catalogservice.vo.ResponseCatalog;

import java.util.List;

public interface CatalogService {
    List<ResponseCatalog> getAllCatalogs();
}
