import { useState } from "react";
import { Box, Divider, Stack } from "@mui/material";
import { DropdownButton } from "./DropdownButton";
import { RequestBody } from "./RequestBody";
import { Response } from "./Response";
import styles from "./styles";
import axios from "axios";

interface PropsType {
  API: any;
  requestBody: any;
}

export const Components: React.FC<PropsType> = (props: PropsType) => {
  const BASE_URL = "";
  const JWTtoken = "";
  // const JWTtoken = localStorage.getItem("jwt");
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
    console.log(data);
    switch (props.API.method) {
      case "GET":
        await axios({
          method: "get",
          url: props.API.uri,
          baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          // `proxy`는 프록시 서버의 호스트이름, 포트, 프로토콜을 정의합니다.
          // 기존의 `http_proxy`와 `https_proxy` 환경 변수를 사용하여
          // 프록시를 정의할 수도 있습니다.
          // 프록시 구성에 환경 변수를 사용하는 경우, 'no_proxy' 환경 변수를
          // 쉼표로 구분된 프록시가 되지 않는 도메인 목록으로 정의할 수도 있습니다.
          // `false`를 사용하면 프록시를 사용하지 않고, 환경 변수를 무시합니다.
          // `auth`는 프록시에 연결하는데 HTTP Basic auth를 사용해야 함을 나타내며,
          // 자격 증명을 제공합니다. 그러면 `Proxy-Authorization` 헤더가 설정되고,
          // `headers`를 사용하여 기존의 `Proxy-Authorization` 사용자 지정 해더를 덮어씁니다.
          // 만약 프록시 서버가 HTTPS를 사용한다면, 프로토콜을 반드시 `https`로 설정해야 합니다.
          // proxy: {
          //   protocol: 'https',
          //   host: '127.0.0.1',
          //   port: 9000,
          //   auth: {
          //     username: 'mikeymike',
          //     password: 'rapunz3l'
          //   }
          // },
        })
          .then((response) => {
            console.log(response);
            setResponseData({
              status: response.status.toString(),
              output: response.data.toString(),
            });
          })
          .catch((err) => {
            console.log(err);
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
          baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          data,
        })
          .then((response) => {
            console.log(response);
            setResponseData({
              status: response.status.toString(),
              output: response.data.toString(),
            });
            if (props.API.detail === "로그인") {
              localStorage.setItem("jwt", response.data.token);
            }
          })
          .catch((err) => {
            console.log(err);
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
          baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          data,
        })
          .then((response) => {
            console.log(response);
            setResponseData({
              status: response.status.toString(),
              output: response.data.toString(),
            });
          })
          .catch((err) => {
            console.log(err);
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
          baseURL: BASE_URL,
          headers: { Authroization: `Bearer ${JWTtoken}` },
          data,
        })
          .then((response) => {
            console.log(response);
            setResponseData({
              status: response.status.toString(),
              output: response.data.toString(),
            });
          })
          .catch((err) => {
            console.log(err);
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
                  <RequestBody
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
