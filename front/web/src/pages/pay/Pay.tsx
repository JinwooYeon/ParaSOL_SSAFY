import AuthBioDELETE from "../../components/pay/authBioDELETE";
import AuthBioPOST from "../../components/pay/authBioPOST";
import DepositPOST from "../../components/pay/depositPOST";
import WithdrawPOST from "../../components/pay/withdrawPOST";
import { Box, Paper, Stack } from "@mui/material";
import { styled } from "@mui/material/styles";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "left",
  color: theme.palette.text.secondary,
}));

export const Pay = () => {
  return (
    <>
      <Box sx={{ width: "100%" }}>
        <Stack spacing={2}>
          <Item>
            <AuthBioPOST />
          </Item>
          <Item>
            <AuthBioDELETE />
          </Item>
          <Item>
            <DepositPOST />
          </Item>
          <Item>
            <WithdrawPOST />
          </Item>
        </Stack>
      </Box>
    </>
  );
};
