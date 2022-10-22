package com.laas.auth.model.args;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarSequenceRequest {

	private String facility;
	private String deliveryCategory;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date etd;
	private String assignmentDistrictId;
	private String hvsRemark;
	private String containerNo;

}
