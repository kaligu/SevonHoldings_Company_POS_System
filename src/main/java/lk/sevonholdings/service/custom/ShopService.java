package lk.sevonholdings.service.custom;

import lk.sevonholdings.dto.ShopDTO;
import lk.sevonholdings.service.SuperService;

import java.util.List;

public interface ShopService extends SuperService {
    String findLastShopNo();
    List<ShopDTO> findAllShops();
}
