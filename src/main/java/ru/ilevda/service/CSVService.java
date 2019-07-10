package ru.ilevda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import ru.ilevda.interfaces.*;
import ru.ilevda.model.User;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@ConfigurationProperties(prefix = "csvservice")
public class CSVService implements FileService {

    private FileHandler fileHandler;
    private GetDataFromRest getDataFromRest;
    private Operations operations;
    private Fee fee;
    private String[] failFileHead;
    private String[] successFileHead;

    public void setFailFileHead(String[] failFileHead) {
        this.failFileHead = failFileHead;
    }

    public void setSuccessFileHead(String[] successFileHead) {
        this.successFileHead = successFileHead;
    }

    @Autowired
    public CSVService(FileHandler fileHandler, GetDataFromRest getDataFromRest, Operations operations, Fee fee) {
        this.fileHandler = fileHandler;
        this.getDataFromRest = getDataFromRest;
        this.operations = operations;
        this.fee = fee;

    }

    @Override
    public void service(String directory) {

        final LocalDate today = LocalDate.now();
        String path = directory + (today.minusDays(1)).format(DateTimeFormatter.ofPattern("ddMMuuuu")) + ".csv";
        String createFileToSuccessFee = directory + (today.minusDays(1)).format(DateTimeFormatter.ofPattern("ddMMuuuu")) + "_success" + ".csv";
        String createFileToFailFee = directory + (today.minusDays(1)).format(DateTimeFormatter.ofPattern("ddMMuuuu")) + "_failure" + ".csv";

        File file = new File(path);

        if (file.exists()) {

            List<String[]> dataList = fileHandler.read(file);

            List<User> userList = operations.parseToUser(dataList);

            // getDataFromRest.get(userList);
            operations.spreadingPerson(userList);

            operations.spreadingPerson(userList).forEach((isSuccessful, users) -> {
                if (isSuccessful)
                    fileHandler.write(new File(createFileToSuccessFee), successFileHead, operations.parseToSuccess(fee.paymentFee(users)));
                else fileHandler.write(new File(createFileToFailFee), failFileHead, operations.parseToFailure(users));
            });


        }
    }
}
