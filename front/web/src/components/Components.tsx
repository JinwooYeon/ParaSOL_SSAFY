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
    await axios
      .post(props.API.uri, data, {
        headers: {
          "content-Type": "application/json",
        },
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
