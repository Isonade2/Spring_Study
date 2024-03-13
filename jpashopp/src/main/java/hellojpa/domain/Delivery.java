package hellojpa.domain;


import jakarta.persistence.*;

@Entity
public class Delivery extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;
}
