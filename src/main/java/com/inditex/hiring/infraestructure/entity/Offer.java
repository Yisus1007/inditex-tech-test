package com.inditex.hiring.infraestructure.entity;



import com.inditex.hiring.domain.dto.OfferDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "Offer")
public class Offer {

	@Id
	@Column(name = "OFFER_ID")
	private Integer offerId;

	@Column(name = "BRAND_ID")
	private Integer brandId;

	@Column(name = "START_DATE")
	private Timestamp startDate;

	@Column(name = "END_DATE")
	private Timestamp endDate;

	@Column(name = "PRICE_LIST")
	private Integer priceListId;

	@Column(name = "PARTNUMBER")
	private String productPartnumber;

	@Column(name = "PRIORITY")
	private Integer priority;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "CURR")
	private String currencyIso;

	@Column(name = "SIZE")
	private String size;

	@Column(name = "MODEL")
	private String model;

	@Column(name = "quality")
	private String quality;

	public static Offer fromDtoOffer(OfferDto offerDto) {
		return Offer.builder()
				.offerId(offerDto.getOfferId().intValue())
				.brandId(offerDto.getBrandId())
				.startDate(Timestamp.valueOf(offerDto.getStartDate()))
				.endDate(Timestamp.valueOf(offerDto.getEndDate()))
				.priceListId(offerDto.getPriceListId().intValue())
				.productPartnumber(offerDto.getProductPartnumber())
				.priority(offerDto.getPriority())
				.price(offerDto.getPrice())
				.currencyIso(offerDto.getCurrencyIso())
				.size(offerDto.getProductPartnumber().substring(0, 2))
				.model(offerDto.getProductPartnumber().substring(2, 6))
				.quality(offerDto.getProductPartnumber().substring(6, 9))
				.build();
	}

	public OfferDto toDtoOffer() {
		return OfferDto.builder()
				.offerId(offerId.longValue())
				.brandId(brandId)
				.startDate(startDate.toString())
				.endDate(endDate.toString())
				.priceListId(priceListId.longValue())
				.productPartnumber(productPartnumber)
				.priority(priority)
				.price(price)
				.currencyIso(currencyIso)
				.build();
	}

}
