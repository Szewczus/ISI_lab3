package front;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHelper {
    private final ObjectMapper objectMapper;
    private final ConnectionBean connectionBean;
    private final String TAG = "sample.ConnectionHelper";
    public ConnectionHelper(){
        objectMapper = new ObjectMapper();
        connectionBean = new ConnectionBean("http://localhost:8082/data");
    }

    public List<DataDto> showData() throws IOException {
        String data = connectionBean.getData("/getAll");
        if (data != null && !data.isEmpty()){
            List<DataDto> dataList = objectMapper.readValue(data, new TypeReference<List<DataDto>>(){});
            return dataList;
        }
        return new ArrayList<>();
    }

    public DataDto saveData(DataDto dataDto) throws IOException {
        String data = connectionBean.postObject(dataDto,"/saveAll");
        if (data != null && !data.isEmpty()){
            DataDto dataList = objectMapper.readValue(data, new TypeReference<DataDto>(){});
            return dataList;
        }
        return null;
    }


}
