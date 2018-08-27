package program.models.macs;

public class MacAddress implements IMacAddress {
    private int id;
    private String value;
    private int portId;
    private String lastSeen;
    private String dateCreated;
    private int userId;

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public int getPortId() {
        return this.portId;
    }

    @Override
    public int getUserId() {
        return this.userId;
    }

    @Override
    public String getLastSeen() {
        return this.lastSeen;
    }

    @Override
    public String getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
