//package com.example.ridesservice.service.impl;
//
//import com.example.ridesservice.dto.request.*;
//import com.example.ridesservice.kafka.RidesProducer;
//import com.example.ridesservice.model.Ride;
//import com.example.ridesservice.repo.RidesRepo;
//import com.example.ridesservice.service.SequenceGeneratorService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static com.example.ridesservice.util.TestUtils.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//public class RidesServiceImplTest {
//
//    @Mock
//    private RidesRepo ridesRepo;
//    @Mock
//    private RidesProducer ridesProducer;
//    @Mock
//    private SequenceGeneratorService service;
//    @InjectMocks
//    private RidesServiceImpl ridesService;
//
//    @Test
//    void createTrip() {
//        PassengerRequestForRide request = getDefaultPassengerRequestForRide();
//        doReturn(DEFAULT_ID).when(service).getSequenceNumber(DEFAULT_SEQUENCE_NAME);
//        doNothing().when(ridesProducer).sendMessage(any(DriverRequest.class));
//        doReturn(getDefaultRide()).when(ridesRepo).save(any(Ride.class));
//
//        ridesService.createTrip(request);
//
//        verify(service).getSequenceNumber(DEFAULT_SEQUENCE_NAME);
//        verify(ridesRepo).save(any(Ride.class));
//        verify(ridesProducer).sendMessage(any(DriverRequest.class));
//    }
//
//    @Test
//    void provideTrip() {
//        RideRequest request = getDefaultRideRequest();
//        doReturn(Optional.of(getDefaultRide())).when(ridesRepo).findById(DEFAULT_ID);
//        doReturn(getDefaultRide()).when(ridesRepo).save(any(Ride.class));
//        doNothing().when(ridesProducer).delegateRating(any(DelegateRatingRequest.class));
//        doNothing().when(ridesProducer).delegatePayment(any(DelegatePaymentRequest.class));
//
//
//        ridesService.provideTrip(request);
//
//        verify(ridesRepo).findById(DEFAULT_ID);
//        verify(ridesRepo).save(any(Ride.class));
//        verify(ridesProducer).delegateRating(any(DelegateRatingRequest.class));
//        verify(ridesProducer).delegatePayment(any(DelegatePaymentRequest.class));
//    }
//}
