import React from "react";
import { Box, Stack, TextField, IconButton } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import styles from "./styles";

interface IMyprops {
  requestBody: any;
  formData: any;
  setFormData: (formDate: any) => void;
}

export const RequestBody: React.FC<IMyprops> = (props: IMyprops) => {
  const myRequest = props.requestBody;
  const formData = props.formData;
  const setter = props.setFormData;

  const handleChange = (key: string, v: string) => {
    setter({
      ...formData,
      [key]: v,
    });
  };

  const clearValue = (key: string) => {
    setter({
      ...formData,
      [key]: "",
    });
  };

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
                      {re.value && (
                        <span style={styles.apiTitle}>{re.value}</span>
                      )}
                      {re.type && <span style={styles.apiType}>{re.type}</span>}
                    </div>
                    {re.required ? (
                      <div style={styles.apiRequired.required}>required</div>
                    ) : (
                      <div style={styles.apiRequired.notRequired}>
                        not required
                      </div>
                    )}
                  </Stack>
                  <TextField
                    label={re.value}
                    style={{ width: "55%" }}
                    size="small"
                    type="text"
                    onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
                      handleChange(re.value, e.target.value);
                    }}
                    value={formData[re.value] || ""}
                    InputProps={{
                      endAdornment: (
                        <IconButton
                          size="small"
                          onClick={() => clearValue(re.value)}
                        >
                          <DeleteIcon />
                        </IconButton>
                      ),
                    }}
                  ></TextField>
                </Stack>
              );
            })
          )}
        </Box>
      ) : null}
    </>
  );
};
