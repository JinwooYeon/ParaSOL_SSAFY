import React from "react";
import { Box, Stack, TextField } from "@mui/material";

interface IMyprops {
  requestBody: {
    LoginInfo: Array<object>;
  };
}

export const RequestBody: React.FC<IMyprops> = (props: IMyprops) => {
  const myRequest = props.requestBody.LoginInfo;

  console.log(props.requestBody.LoginInfo);
  console.log(JSON.stringify(props.requestBody));
  return (
    <>
      {props.requestBody ? (
        <Box>
          {myRequest.map((re: any) => {
            return (
              <Stack
                direction="row"
                justifyContent="space-between"
                spacing={3}
                sx={{ marginTop: 1 }}
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
                ></TextField>
              </Stack>
            );
          })}
        </Box>
      ) : null}
    </>
  );
};
