import { TextField } from "@mui/material";
import { Controller, useForm } from "react-hook-form";

export const InputController = ({ control }: any) => {
  return (
    <Controller
      name="request"
      control={control}
      defaultValue=""
      rules={{ required: { value: true, message: "입력값을 넣어주세요." } }}
      render={({ field }) => (
        <TextField
          {...field}
          label="입력값"
          type="text"
          multiline
          rows={9}
          sx={{ width: "100%" }}
        />
      )}
    />
  );
};
