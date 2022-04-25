import React, { useState } from "react";
import { Box, Stack, TextField } from "@mui/material";
import { useEffect } from "react";

interface IMyprops {
  requestBody: any;
  // requestBody: {
  //   LoginInfo: Array<object>;
  // };
  formData: object;
  setFormData: (formDate: any) => void;
}

export const RequestBody: React.FC<IMyprops> = (props: IMyprops) => {
  // const myRequest = props.requestBody.LoginInfo;
  const myRequest = props.requestBody;
  const formData = props.formData;
  const setter = props.setFormData;

  const [inputData, setInputData] = useState(formData);

  // console.log(props.requestBody.LoginInfo);
  // console.log(JSON.stringify(props.requestBody));

  const handleChange = (key: string, value: string) => {
    // console.log(key, value);
    switch (key) {
      case "id": {
        setInputData({
          ...inputData,
          id: value,
        });
        return;
      }
      case "password": {
        setInputData({
          ...inputData,
          password: value,
        });
        return;
      }
      case "name": {
        setInputData({
          ...inputData,
          name: value,
        });
        return;
      }
      default: {
        setInputData(inputData);
        return;
      }
    }
  };

  useEffect(() => {
    setter(inputData);
  }, [inputData]);

  return (
    <>
      {props.requestBody ? (
        <Box>
          {Object.keys(myRequest).map((key: string) =>
            myRequest[key].map((re: any, index: string) => {
              return (
                <Stack
                  direction="row"
                  justifyContent="space-between"
                  spacing={3}
                  sx={{ marginTop: 1 }}
                  key={index}
                >
                  <Stack
                    direction="row"
                    spacing={1}
                    alignItems="center"
                    justifyContent="space-between"
                    sx={{ width: "40%" }}
                  >
                    <div>
                      {re.value} : {re.type}
                    </div>
                    {re.required ? (
                      <div style={{ color: "blue" }}>required</div>
                    ) : (
                      <div style={{ color: "red" }}>not required</div>
                    )}
                  </Stack>
                  <TextField
                    label={re.value}
                    style={{ width: "60%" }}
                    size="small"
                    onChange={(e) => {
                      handleChange(re.value, e.target.value);
                    }}
                  ></TextField>
                </Stack>
              );
            })
          )}
          {/* {myRequest.map((re: any, index: any) => {
            return (
              <Stack
                direction="row"
                justifyContent="space-between"
                spacing={3}
                sx={{ marginTop: 1 }}
                key={index}
              >
                <Stack
                  direction="row"
                  spacing={1}
                  alignItems="center"
                  justifyContent="space-between"
                  sx={{ width: "40%" }}
                >
                  <div>
                    {re.value} : {re.type}
                  </div>
                  {re.required ? (
                    <div style={{ color: "blue" }}>required</div>
                  ) : (
                    <div style={{ color: "red" }}>not required</div>
                  )}
                </Stack>
                <TextField
                  label={re.value}
                  style={{ width: "60%" }}
                  size="small"
                  onChange={(e) => {
                    handleChange(re.value, e.target.value);
                  }}
                ></TextField>
              </Stack>
            );
          })} */}
        </Box>
      ) : null}
    </>
  );
};
