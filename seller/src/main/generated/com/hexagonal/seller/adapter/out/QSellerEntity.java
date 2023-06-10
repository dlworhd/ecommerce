package com.hexagonal.seller.adapter.out;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSellerEntity is a Querydsl query type for SellerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSellerEntity extends EntityPathBase<SellerEntity> {

    private static final long serialVersionUID = -94219392L;

    public static final QSellerEntity sellerEntity = new QSellerEntity("sellerEntity");

    public final StringPath email = createString("email");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath password = createString("password");

    public final StringPath registrationNum = createString("registrationNum");

    public final EnumPath<SellerStatus> sellerStatus = createEnum("sellerStatus", SellerStatus.class);

    public QSellerEntity(String variable) {
        super(SellerEntity.class, forVariable(variable));
    }

    public QSellerEntity(Path<? extends SellerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSellerEntity(PathMetadata metadata) {
        super(SellerEntity.class, metadata);
    }

}

