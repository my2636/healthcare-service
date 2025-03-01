package ru.netology.patient.medical;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicalServiceImplTest {

    static PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
    static SendAlertService alertService = Mockito.spy(SendAlertServiceImpl.class);
    // Написать тесты для проверки класса MedicalServiceImpl, сделав заглушку для класса PatientInfoFileRepository, который он использует

    @Test
    void medicalServiceMessageOutput1() {
        Mockito.when(patientInfoRepository.getById("1")).thenReturn(new PatientInfo("1", "John", "Doe",
                LocalDate.of(1998, 4, 30), new HealthInfo(new BigDecimal(36.6),new BloodPressure(192, 97))));

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure("1", new BloodPressure(120, 60));

        //  - Проверить вывод сообщения во время проверки давления checkBloodPressure
        Mockito.verify(alertService, Mockito.times(1)).send(Mockito.anyString());
    }

    @Test
    void medicalServiceMessageOutput2() {
        Mockito.when(patientInfoRepository.getById("2")).thenReturn(new PatientInfo("2", "Jane", "Doe",
                LocalDate.of(1989, 8, 1), new HealthInfo(new BigDecimal(39.3),new BloodPressure(120, 60))));

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature("2", new BigDecimal(36.6));

        // - Проверить вывод сообщения во время проверки температуры checkTemperature
        Mockito.verify(alertService, Mockito.times(1)).send(Mockito.anyString());
    }

    @Test
    void medicalServiceMessageOutput3() {
        Mockito.when(patientInfoRepository.getById("3")).thenReturn(new PatientInfo("3", "Ann", "Doe",
                LocalDate.of(2011, 5, 26), new HealthInfo(new BigDecimal(36.6),new BloodPressure(120, 60))));

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure("3", new BloodPressure(120, 60));
        medicalService.checkTemperature("3", new BigDecimal(36.6));

        //  - Проверить, что сообщения не выводятся, когда показатели в норме.
        Mockito.verify(alertService, Mockito.times(0)).send(Mockito.anyString());
    }

}
