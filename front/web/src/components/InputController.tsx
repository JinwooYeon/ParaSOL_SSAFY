import { TextField } from "@mui/material";
import { Controller } from "react-hook-form";

export const InputController = ({ control, label }: any) => {
  return (
    <Controller
      name={label}
      control={control}
      defaultValue=""
      rules={{ required: { value: true, message: "입력값을 넣어주세요." } }}
      render={({ field }) => (
        <TextField
          {...field}
          label={label}
          type="text"
          sx={{ width: "100%" }}
        />
      )}
    />
  );
};
