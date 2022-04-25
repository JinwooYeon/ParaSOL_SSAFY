import { useState } from "react";
import { Stack, Divider, Box, Button } from "@mui/material";
import { useForm, SubmitHandler } from "react-hook-form";
import axios from "axios";
import { Completed } from "components/Completed";
import { InputController } from "components/InputController";
import "../../Styles.css";

export const UserPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/client",
    method: "POST",
    detail: "회원 등록",
    completed: false,
  };
  const requestBody = {
    ClientInfo: [
      {
        value: "id",
        type: "string",
        required: true,
      },
      {
        value: "password",
        type: "string",
        required: true,
      },
    ],
  };
  ///////////////////////////////////

  const [show, setShow] = useState(false);
  const [output, setOutput] = useState("");
  const { handleSubmit, control } = useForm({});

  const handlShow = () => {
    setShow(!show);
  };

  const onSubmit: SubmitHandler<any> = async (data) => {
    console.log(data);
    const inputData = JSON.stringify(data);
    await axios
      .post("/client", inputData, {
        headers: {
          "content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response);
        setOutput(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <Button sx={styles.apiHeader} onClick={handlShow}>
        <Stack
          direction="row"
          justifyContent="space-between"
          sx={{ width: "100%" }}
        >
          <Stack direction="row" alignItems="center" spacing={2}>
            <Completed completed={API.completed} />
            <Box style={styles.apiTitle}>{API.detail}</Box>
          </Stack>
          <Stack direction="row" alignItems="center" spacing={2}>
            <Box style={styles.apiMethod}>{API.method}</Box>
            <Box sx={styles.apiUri}>{API.uri}</Box>
          </Stack>
        </Stack>
      </Button>
      {show ? (
        <Stack>
          <Divider sx={{ mt: 1, mb: 3 }} />
          <Stack spacing={2} sx={styles.api}>
            <Stack direction="column">
              <Box style={{ fontWeight: "bold" }}>API 요청</Box>
              <Box style={styles.apiRequest}>
                <form onSubmit={handleSubmit(onSubmit)}>
                  <Stack direction="column" spacing={3}>
                    {requestBody ? (
                      <Box>
                        {Object.values(requestBody).map((arr: any) =>
                          arr.map((re: any) => (
                            <Stack
                              direction="row"
                              justifyContent="space-between"
                              spacing={5}
                              sx={{ marginTop: 1 }}
                              key={re.value}
                            >
                              <Stack
                                direction="row"
                                spacing={1}
                                alignItems="center"
                                justifyContent="space-between"
                                sx={{ width: "70%" }}
                              >
                                <div>
                                  <span
                                    style={{ fontSize: 20, fontWeight: "bold" }}
                                  >
                                    {re.value}
                                  </span>
                                  <span
                                    style={{ marginLeft: 10, opacity: "70%" }}
                                  >
                                    {re.type}
                                  </span>
                                </div>
                                {re.required ? (
                                  <div style={{ color: "blue" }}>required</div>
                                ) : (
                                  <div style={{ color: "red" }}>
                                    not required
                                  </div>
                                )}
                              </Stack>
                              <InputController
                                control={control}
                                label={re.value}
                              ></InputController>
                            </Stack>
                          ))
                        )}
                      </Box>
                    ) : null}
                    <Button variant="contained" type="submit" color="primary">
                      출력값 확인하기
                    </Button>
                  </Stack>
                </form>
              </Box>
            </Stack>
            <Stack justifyContent="center" alignItems="center">
              <Box sx={{ fontWeight: "bold" }}>출력값</Box>
              <Box style={styles.apiResponse}>
                <p>{output}</p>
              </Box>
            </Stack>
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};

const styles = {
  api: {
    padding: "2px 40px 0px 40px",
    color: "black",
  },
  apiHeader: {
    width: "100%",
    justifyContent: "start",
    color: "black",
  },
  apiUri: {
    color: "blue",
    fontSize: 15,
    alignItems: "center",
    textTransform: "lowercase",
  },
  apiMethod: {
    background: "blue",
    color: "white",
    padding: "4px 10px",
    fontSize: 13,
    borderRadius: 20,
  },
  apiTitle: {
    fontSize: 20,
    fontWeight: "bold",
    padding: 4,
    alignItems: "center",
  },
  apiRequest: {
    borderRadius: 10,
    marginTop: 10,
    padding: 13,
    height: "100%",
  },
  apiResponse: {
    border: "solid blue 2px",
    borderRadius: 10,
    marginTop: 10,
    padding: 5,
    width: "95%",
    height: 100,
  },
};
