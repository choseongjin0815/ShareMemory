package com.chobocho.ShareMemory_back_end.domain.diary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiaryImages is a Querydsl query type for DiaryImages
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDiaryImages extends BeanPath<DiaryImages> {

    private static final long serialVersionUID = -1847643224L;

    public static final QDiaryImages diaryImages = new QDiaryImages("diaryImages");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Integer> ord = createNumber("ord", Integer.class);

    public QDiaryImages(String variable) {
        super(DiaryImages.class, forVariable(variable));
    }

    public QDiaryImages(Path<? extends DiaryImages> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiaryImages(PathMetadata metadata) {
        super(DiaryImages.class, metadata);
    }

}

