package com.hpe.service;

import java.util.List;

import com.hpe.pojo.Rest;

public interface RestService {

	List<Rest> restList();

	int insertRest(Rest rest);

	Rest selectRest(Rest rest);

}
