package com.fastcampus.pass.repository.notification;

import com.fastcampus.pass.repository.booking.BookingEntity;
import com.fastcampus.pass.repository.user.UserEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-01T00:45:48+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
public class NotificationModelMapperImpl implements NotificationModelMapper {

    @Override
    public NotificationEntity toNotificationEntity(BookingEntity bookingEntity, NotificationEvent event) {
        if ( bookingEntity == null && event == null ) {
            return null;
        }

        NotificationEntity notificationEntity = new NotificationEntity();

        if ( bookingEntity != null ) {
            notificationEntity.setUuid( bookingEntityUserEntityUuid( bookingEntity ) );
            notificationEntity.setText( text( bookingEntity.getStartedAt() ) );
        }
        if ( event != null ) {
            notificationEntity.setEvent( event );
        }

        return notificationEntity;
    }

    private String bookingEntityUserEntityUuid(BookingEntity bookingEntity) {
        if ( bookingEntity == null ) {
            return null;
        }
        UserEntity userEntity = bookingEntity.getUserEntity();
        if ( userEntity == null ) {
            return null;
        }
        String uuid = userEntity.getUuid();
        if ( uuid == null ) {
            return null;
        }
        return uuid;
    }
}
