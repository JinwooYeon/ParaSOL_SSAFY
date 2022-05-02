import { useState } from "react";
import { Box, Divider, Stack } from "@mui/material";
import { DropdownButton } from "./DropdownButton";
import { Request } from "./Request";
import { Response } from "./Response";
import styles from "./styles";
import axios from "axios";

interface PropsType {
  API: any;
  requestBody: any;
}

export const Components: React.FC<PropsType> = (props: PropsType) => {
  // const BASE_URL = "http://k6S101.p.ssafy.io:8080/";
  const JWTtoken = localStorage.getItem("jwt")
    ? localStorage.getItem("jwt")
    : "";
  const [show, setShow] = useState(false);
  const [formData, setFormData] = useState({});
  const [responseData, setResponseData] = useState({
    status: "",
    output: "",
  });

  const handleShow = () => {
    setShow(!show);
  };
  const onSubmit = async (data: any) => {
    switch (props.API.method) {
      case "GET":
        await axios({
          method: "get",
          url: props.API.uri,
          // baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          params: data,
        })
          .then((response) => {
            console.log(response);
            setResponseData({
              status: response.status.toString(),
              output: JSON.stringify(response.data),
            });
          })
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      case "POST":
        await axios({
          method: "post",
          url: props.API.uri,
          // baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          data,
        })
          .then((response) => {
            setResponseData({
              status: response.status.toString(),
              output: response.data.toString(),
            });
            if (props.API.detail === "로그인") {
              localStorage.setItem("jwt", response.data.token);
            }
          })
          // .catch(function (error) {
          //   console.log(error);
          //   if (error.response) {
          //     // 요청이 이루어졌으며 서버가 2xx의 범위를 벗어나는 상태 코드로 응답했습니다.
          //     console.log("first");
          //     console.log(error.response.data);
          //     console.log(error.response.status);
          //     console.log(error.response.headers);
          //   } else if (error.request) {
          //     // 요청이 이루어 졌으나 응답을 받지 못했습니다.
          //     // `error.request`는 브라우저의 XMLHttpRequest 인스턴스 또는
          //     // Node.js의 http.ClientRequest 인스턴스입니다.
          //     console.log("second");
          //     console.log(error.request);
          //   } else {
          //     // 오류를 발생시킨 요청을 설정하는 중에 문제가 발생했습니다.
          //     console.log("third");
          //     console.log("Error", error.message);
          //   }
          //   console.log(error.config);
          //   setResponseData({
          //     status: error.response.status.toString(),
          //     output: error.response.data.toString(),
          //   });
          // });
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      case "PATCH":
        await axios({
          method: "patch",
          url: props.API.uri,
          // baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          data,
        })
          .then((response) => {
            setResponseData({
              status: response.status.toString(),
              output: response.data.toString(),
            });
          })
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      case "DELETE":
        await axios({
          method: "delete",
          url: props.API.uri,
          // baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          data,
        })
          .then((response) => {
            setResponseData({
              status: response.status.toString(),
              output: response.data.toString(),
            });
          })
          .catch((err) => {
            setResponseData({
              status: err.response.status.toString(),
              output: err.toString(),
            });
          });
        break;
      default:
        console.log("axios error");
    }
  };

  return (
    <>
      <DropdownButton API={props.API} handleShow={handleShow} />
      {show ? (
        <Stack>
          <Divider sx={{ mt: 1, mb: 3 }} />
          <Stack spacing={2} sx={styles.apiSection}>
            <Stack direction="column">
              <Box>
                <Stack direction="column" spacing={3}>
                  <Request
                    requestBody={props.requestBody}
                    formData={formData}
                    setFormData={setFormData}
                    onSubmit={onSubmit}
                  />
                </Stack>
              </Box>
            </Stack>
            <Response
              responseData={responseData}
              setResponseData={setResponseData}
            />
          </Stack>
        </Stack>
      ) : null}
    </>
  );
};
