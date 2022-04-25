import { useState } from "react";
import { Stack, Divider, Grid, Box, Button } from "@mui/material";
import { InputController } from "components/InputController";
import { useForm, SubmitHandler } from "react-hook-form";
import axios from "axios";
import { Completed } from "components/Completed";
import { RequestBody } from "components/RequestBody";

export const AuthPOST = () => {
  const API = {
    uri: "/auth",
    method: "POST",
    detail: "로그인",
    completed: false,
  };
  const requestBody = {
    LoginInfo: [
      {
        value: "id",
        type: "string",
        required: false,
      },
      {
        value: "password",
        type: "string",
        required: true,
      },
    ],
  };

  let defaultObject = {};
  Object.values(requestBody).map((val) => {
    val.map((v) => {
      const tempObj = { [v.value]: "" };
      defaultObject = Object.assign(defaultObject, tempObj);
    });
  });

  const responseBody = {};
  const [show, setShow] = useState(false);
  const [output, setOutput] = useState("");
  // const { handleSubmit, control } = useForm({});
  const [formData, setFormData] = useState(defaultObject);
  // const [formData, setFormData] = useState({
  //   id: "",
  //   password: "",
  // });

  const handlShow = () => {
    setShow(!show);
  };

  const onSubmit: SubmitHandler<any> = async (data) => {
    console.log(data);
    // const inputData = JSON.stringify(data);
    const inputData = data;
    await axios
      .post("/auth", inputData, {
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
            <Box style={styles.apiDetail}>{API.detail}</Box>
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
              <Box style={styles.apiContent}>API 요청</Box>
              <Box style={styles.apiContentDetail}>
                {/* <form onSubmit={handleSubmit(onSubmit)}> */}
                <Stack direction="column" spacing={3}>
                  <RequestBody
                    requestBody={requestBody}
                    formData={formData}
                    setFormData={setFormData}
                  />
                  <Button
                    variant="contained"
                    type="button"
                    color="success"
                    onClick={() => onSubmit(formData)}
                  >
                    출력값 확인하기
                  </Button>
                </Stack>
                {/* </form> */}
              </Box>
            </Stack>
            {/* <Grid container spacing={3}>
              <Grid item xs={5}>
                <form onSubmit={handleSubmit(onSubmit)}>
                  <Stack
                    direction="column"
                    justifyContent="space-between"
                    alignItems="center"
                  >
                    <InputController control={control} />
                    <Button type="submit">출력값 확인하기</Button>
                  </Stack>
                </form>
              </Grid>
              <Grid item xs={5}>
                <Stack alignItems="center">
                  <Box style={styles.outputStyle}>
                    <p>{output}</p>
                  </Box>
                  <Box sx={{ marginTop: "5px" }}>출력값</Box>
                </Stack>
              </Grid>
            </Grid> */}
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};

const styles = {
  api: {
    // margin: "auto 10px",
    padding: "2px 40px",
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
    background: "green",
    color: "white",
    padding: "4px 10px",
    fontSize: 13,
    borderRadius: 20,
  },
  apiDetail: {
    fontSize: 20,
    fontWeight: "bold",
    padding: 4,
    alignItems: "center",
  },
  apiContent: {
    color: "black",
    fontWeight: "bold",
  },
  apiContentDetail: {
    backgroundColor: "#D8D6D6",
    borderRadius: 10,
    margin: "15px auto",
    padding: 13,
    width: "100%",
    height: "100%",
  },
  outputStyle: {
    border: "solid green 3px",
    padding: 9,
    width: "90%",
    height: 217,
  },
};
