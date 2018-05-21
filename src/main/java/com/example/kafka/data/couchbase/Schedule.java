package com.example.kafka.data.couchbase;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

@Document
public class Schedule {

    @Id
    private String id;

    @Field
    private long version;

    @Field
    private String flightNumber;

    @Field
    private List<LegInformation> legs;

    @Field
    private boolean hosted;

    public static class LegInformation {
        private String id;
        private int order;
        private String departureCityCode;
        private int departureDateDiscrepancyNumber;
        private String departureTerminalCode;
        private String arrivalCityCode;
        private int arrivalDateAdjustmentNumber;
        private String arrivalTerminalCode;
        private EquipmentInformation equipmentInformation;
        private String mctCode;
        private String trafficRestrictionCode;

        public LegInformation(
            String id,
            int order,
            String departureCityCode,
            int departureDateDiscrepancyNumber,
            String departureTerminalCode,
            String arrivalCityCode,
            int arrivalDateAdjustmentNumber,
            String arrivalTerminalCode,
            EquipmentInformation equipmentInformation,
            String mctCode,
            String trafficRestrictionCode
        ) {
            this.id = id;
            this.order = order;
            this.departureCityCode = departureCityCode;
            this.departureDateDiscrepancyNumber = departureDateDiscrepancyNumber;
            this.departureTerminalCode = departureTerminalCode;
            this.arrivalCityCode = arrivalCityCode;
            this.arrivalDateAdjustmentNumber = arrivalDateAdjustmentNumber;
            this.arrivalTerminalCode = arrivalTerminalCode;
            this.equipmentInformation = equipmentInformation;
            this.mctCode = mctCode;
            this.trafficRestrictionCode = trafficRestrictionCode;
        }
    }

    public static class EquipmentInformation {
        private String id;
        private String equipmentTypeCode;
        private String equipmentCode;
        private String bookingDesignatorText;
        private String bookingModifierText;
        private String aircraftConfigurationText;

        public EquipmentInformation(
            String id,
            String equipmentTypeCode,
            String equipmentCode,
            String bookingDesignatorText,
            String bookingModifierText,
            String aircraftConfigurationText
        ) {
            this.id = id;
            this.equipmentTypeCode = equipmentTypeCode;
            this.equipmentCode = equipmentCode;
            this.bookingDesignatorText = bookingDesignatorText;
            this.bookingModifierText = bookingModifierText;
            this.aircraftConfigurationText = aircraftConfigurationText;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<LegInformation> getLegs() {
        return legs;
    }

    public void setLegs(List<LegInformation> legs) {
        this.legs = legs;
    }

    public boolean isHosted() {
        return hosted;
    }

    public void setHosted(boolean hosted) {
        this.hosted = hosted;
    }

    @Override
    public String toString() {
        return "Schedule{" +
            "id='" + id + '\'' +
            ", version=" + version +
            ", flightNumber='" + flightNumber + '\'' +
            ", legs=" + legs +
            ", hosted=" + hosted +
            '}';
    }
}
